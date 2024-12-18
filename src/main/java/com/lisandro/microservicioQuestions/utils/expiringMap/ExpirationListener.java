package com.lisandro.microservicioQuestions.utils.expiringMap;

public interface ExpirationListener<E> {
    void expired(E expiredObject);
}
