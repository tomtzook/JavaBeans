package com.beans.observables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Observables {

    private Observables() {}

    private static volatile ScheduledExecutorService sExecutorService;

    private static volatile ObservableFactory sFactory;
    private static volatile PollingObservableFactory sPollingFactory;

    public static ObservableFactory factory() {
         if (sFactory == null) {
             synchronized (Observables.class) {
                 if (sFactory == null) {
                     sFactory = new ObservableFactory(executorService());
                 }
             }
         }

         return sFactory;
    }

    public synchronized static void setFactory(ObservableFactory factory) {
        if (sFactory != null) {
            throw new IllegalStateException("already initialized");
        }
        sFactory = factory;
    }

    public static PollingObservableFactory pollingFactory() {
        if (sPollingFactory == null) {
            synchronized (Observables.class) {
                if (sPollingFactory == null) {
                    sPollingFactory = new PollingObservableFactory(factory(), executorService());
                }
            }
        }

        return sPollingFactory;
    }

    public synchronized static void setPollingFactory(PollingObservableFactory pollingFactory) {
        if (sPollingFactory != null) {
            throw new IllegalStateException("already initialized");
        }
        sPollingFactory = pollingFactory;
    }

    public synchronized static void setExecutorService(ScheduledExecutorService executorService) {
        if (sExecutorService != null) {
            throw new IllegalStateException("already initialized");
        }
        sExecutorService = executorService;
    }

    private static ScheduledExecutorService executorService() {
        if (sExecutorService == null) {
            synchronized (Observables.class) {
                if (sExecutorService == null) {
                    sExecutorService = Executors.newScheduledThreadPool(2);
                    Runtime.getRuntime().addShutdownHook(new ShutdownThread(sExecutorService));
                }
            }
        }

        return sExecutorService;
    }

    private static class ShutdownThread extends Thread {

        private final ExecutorService mExecutorService;

        private ShutdownThread(ExecutorService executorService) {
            mExecutorService = executorService;
        }

        @Override
        public void run() {
            mExecutorService.shutdownNow();
        }
    }
}
