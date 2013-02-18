/*
 * Copyright (C) 2012 Yuriy Kulikov yuriy.kulikov.87@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.better.alarm;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

import com.better.alarm.model.AlarmCore;
import com.better.alarm.model.Alarms;
import com.better.alarm.model.AlarmsManager;
import com.better.alarm.model.AlarmsScheduler;
import com.better.alarm.model.persistance.AlarmDatabaseHelper;
import com.better.alarm.model.persistance.AlarmProvider;
import com.github.androidutils.logger.FileLogWriter;
import com.github.androidutils.logger.LogcatLogWriterWithLines;
import com.github.androidutils.logger.Logger;
import com.github.androidutils.logger.Logger.LogLevel;
import com.github.androidutils.logger.LoggingExceptionHandler;
import com.github.androidutils.wakelock.WakeLockManager;

@ReportsCrashes(formKey = "dEZBaFYxLVVRbDl0ZHAyMElab0d3NWc6MQ")
public class AlarmApplication extends Application {

    @Override
    public void onCreate() {
        // The following line triggers the initialization of ACRA
        ACRA.init(this);

        Logger logger = Logger.getDefaultLogger();
        logger.addLogWriter(new LogcatLogWriterWithLines());
        logger.addLogWriter(new FileLogWriter("BetterAlarm"));
        LoggingExceptionHandler.addLoggingExceptionHandlerToAllThreads(logger);
        logger.setLogLevel(WakeLockManager.class, LogLevel.ERR);
        logger.setLogLevel(AlarmsScheduler.class, LogLevel.DEBUG);
        logger.setLogLevel(AlarmCore.class, LogLevel.DEBUG);
        logger.setLogLevel(Alarms.class, LogLevel.DEBUG);
        logger.setLogLevel(AlarmProvider.class, LogLevel.ERR);
        logger.setLogLevel(AlarmDatabaseHelper.class, LogLevel.ERR);

        WakeLockManager.init(getApplicationContext(), logger, true);
        AlarmsManager.init(getApplicationContext(), logger);
        super.onCreate();
    }
}
