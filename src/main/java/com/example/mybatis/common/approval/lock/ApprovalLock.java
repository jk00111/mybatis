package com.example.mybatis.common.approval.lock;

import com.example.mybatis.common.approval.vo.ApprovalId;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


public class ApprovalLock implements Lock<ApprovalId>{

    private final int POOL_SIZE = 5;
    private final Set<CustomSpinLock> pool = new CopyOnWriteArraySet<>();

    public ApprovalLock() {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(new CustomSpinLock());
        }
    }

    /*
    * spinlock 사용 이유
    * -> 결재 단계의 동시성 문제는 같은 식별자를 가진 결재 에서 발생
    * -> 같은 결재에 대한 결재자 많아야 5명 이하일 것으로 예상
    * -> 충돌 가능성이 낮다고 봐 동기화락 사용 X
    * 결재 비즈니스로직(DB 작업)을 포함한 락이라 문제있을까 고려.. -> 더작은단위로 가능할까?
    *
    * 승인단계를 모두 동기화 시키면 비효율적
    * -> 경합이 문제되는 경우는 같은 식별자인 경우
    * -> 식별자 단위 락관리를 구현
    *
    * 락 분배 과정이 여러 원자 단위로 이루어져
    * 락 분배 동시성 해결이 필요해보임..
    * */
    @Override
    public void lock(ApprovalId approvalId) {
        CustomSpinLock lock = getLock(approvalId);

        // 1번이 락을 받고 락을 걸기전에 2번이 락을 받으러 진입하면 문제..
        // 배분 -> 락 실행 사이 점유 단계를 synchronized 에 포함
        lock.lock(approvalId.getId());
    }

    private synchronized CustomSpinLock getLock(ApprovalId approvalId) {
        Integer id = approvalId.getId();
        for (CustomSpinLock lock : pool) {
            if (lock.isOccupy(id)) {
                return lock;
            }
        }

        CustomSpinLock waitLock = waitLock();
        waitLock.occupy(id);
        return waitLock;
    }

    @Override
    public void unlock(ApprovalId approvalId) {
        Integer id = approvalId.getId();
        for (CustomSpinLock lock : pool) {
            if (lock.isOccupy(id)) {
                lock.unlock();
            }
        }
    }

    private CustomSpinLock waitLock() {
        for (CustomSpinLock lock : pool) {
            if (lock.isWait()) {
                return lock;
            }
        }
        throw new RuntimeException("접속량 너무 많음!");
    }
}
