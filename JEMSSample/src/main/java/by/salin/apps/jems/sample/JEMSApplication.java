/*
 * Copyright 2013 Alexandr Salin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package by.salin.apps.jems.sample;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import org.androidannotations.annotations.EApplication;

/**
 * Created by Alexandr.Salin on 25.12.13.
 */
@EApplication
public class JEMSApplication extends Application
{
	private static JEMSApplication instance;

	public static JEMSApplication getInstance()
	{
		return instance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		Crashlytics.start(this);
	}
}
