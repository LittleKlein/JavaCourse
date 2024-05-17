package ru.spring.task5;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionSimple {
    private final PlatformTransactionManager transactionManager;
    private final DefaultTransactionDefinition defTransaction;
    private TransactionStatus status;

    public TransactionSimple(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.defTransaction = new DefaultTransactionDefinition();
    }

    public void begin(String name){
        defTransaction.setName(name);
        status = transactionManager.getTransaction(defTransaction);
    }

    public void commit(){
        transactionManager.commit(status);
    }

    public void rollback(){
        transactionManager.rollback(status);
    }
}
