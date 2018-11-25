package com.chaplin.test3.domain.execution;

import io.reactivex.Scheduler;

public interface ExecutionThread {
    Scheduler getScheduler();
}
