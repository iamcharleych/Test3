package com.chaplin.test3.domain.repository;

import io.reactivex.Flowable;

public interface SessionRepository {
    Flowable<String> setupSession();
}
