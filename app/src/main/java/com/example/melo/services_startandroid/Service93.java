package com.example.melo.services_startandroid;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Melo on 02.09.2018.
 */

public class Service93 extends Service {
    final String LOG_TAG = "myLogs";
    ExecutorService es;
    Object someRes;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        /*
        *Executors.newFixedThreadPool(1) – эта строка дает нам объект (я буду называть его - экзекьютор),
         *  который будет
        * получать от нас задачи (Runnable) и запускать их по очереди в одном потоке
        * (на вход ему мы передаем значение 1). Он сделает за нас всю работу по управлению потоками.
        * */
        es = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
        someRes = null;
    }
/*входящие параметры метода onStartCommand:

Первый – это Intent.  Тот самый, который отправляется в путь, когда мы стартуем сервис с помощью метода startService. Соответственно вы можете использовать его для передачи данных в ваш сервис. Тут все аналогично, как при вызове другого Activity – там вы тоже можете передать данные с помощью intent.

Второй параметр – флаги запуска. Он нам пока не нужен, пропускаем его.

Третий параметр – startId. Простыми словами – это счетчик вызовов startService пока сервис запущен. Т.е. вы запустили сервис методом startService, сработал метод onStartCommand и получил на вход startId = 1. Вызываем еще раз метод startService, сработал метод onStartCommand и получил на вход startId = 2. И так далее. Счетчик сбрасывается,
когда сервис будет остановлен методами stopService, stopSelf и пр. После этого вызовы снова идут с единицы.*/

    /*
    * В onStartCommand  мы читаем из intent параметр time. Создаем Runnable-объект MyRun, передаем ему
    * time и startId и отдаем этот объект экзекьютору, который его запустит в отдельном потоке.*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");
        int time = intent.getIntExtra("time", 1);
        MyRun mr = new MyRun(time, startId);
        es.execute(mr);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }


    /*MyRun – Runnable-объект. Он и будет обрабатывать входящие вызовы сервиса. В конструкторе он получает time и startId. Параметр time будет использован для кол-ва секунд паузы (т.е. эмуляции работы). А startId будет использован в методе stopSelf(startId), который даст сервису понять,
    что вызов под номером strartId обработан. В лог выводим инфу о создании, старте и завершении работы.
    Также здесь используем объект someRes, в лог просто выводим его класс. Если же объект = null, то ловим
    эту ошибку и выводим ее в лог*/
    class MyRun implements Runnable {

        int time;
        int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            //Если выполнение потока прервалось - фиксируем эту ошибку.
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Если же объект = null, то ловим эту ошибку и выводим ее в лог.
            try {
                Log.d(LOG_TAG, "MyRun#" + startId + " someRes = " + someRes.getClass() );
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "MyRun#" + startId + " error, null pointer");
            }
            stop();
        }

        void stop() {
           /* Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelf(" + startId + ")");
            stopSelf(startId);*/
            //Метод не работает - не выдает true or false
            stopSelfResult(startId);
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId + ")");

        }
    }
}
