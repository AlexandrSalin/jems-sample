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

package by.salin.apps.jems.sample.base;

import android.support.v7.app.ActionBarActivity;
import by.salin.apps.jems.EventHandlerCallback;
import by.salin.apps.jems.JEMS;
import by.salin.apps.jems.sample.events.ChangeColorEvent;
import by.salin.apps.jems.sample.events.DefaultEvent;
import by.salin.apps.jems.sample.events.FinishEvent;
import by.salin.apps.jems.sample.events.SpeedChangeEvent;

/**
 * Created by alexander.salin on 19.12.13.
 */
public abstract class BaseActivity extends ActionBarActivity implements EventHandlerCallback
{
	@Override
	protected void onStart()
	{
		super.onStart();
		JEMS.dispatcher().addListenerOnEvent(FinishEvent.class, this);
		JEMS.dispatcher().addListenerOnEvent(SpeedChangeEvent.class, this);
		JEMS.dispatcher().addListenerOnEvent(ChangeColorEvent.class, this);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		JEMS.dispatcher().removeListener(this);
	}
}
