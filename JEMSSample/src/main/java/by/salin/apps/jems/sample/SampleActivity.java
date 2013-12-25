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

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import by.salin.apps.jems.JEMS;
import by.salin.apps.jems.impl.Event;
import by.salin.apps.jems.sample.base.BaseActivity;
import by.salin.apps.jems.sample.events.ChangeColorEvent;
import by.salin.apps.jems.sample.events.DefaultEvent;
import by.salin.apps.jems.sample.events.FinishEvent;
import by.salin.apps.jems.sample.tasks.ChangeColorRunnable;
import by.salin.apps.jems.sample.utils.ColorGenUtils;
import org.androidannotations.annotations.*;

/**
 * @author Alexandr.Salin (salin.alexandr@gmail.com)
 */
@OptionsMenu(R.menu.sample)
@EActivity(R.layout.ac_sample)
public class SampleActivity extends BaseActivity
{
	private static final String LEFT_FRAGMENT = "Left fragment";
	private static final String RIGHT_FRAGMENT = "Right fragment";
	@ViewById(R.id.container)
	protected FrameLayout container;

	@ViewById(R.id.start)
	protected Button button;

	@InstanceState
	protected boolean isStarted = false;

	@AfterViews
	void init()
	{
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.left);
		if (fragment == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(R.id.left, new SampleFragment().newInstance(LEFT_FRAGMENT))
					.add(R.id.right, new SampleFragment().newInstance(RIGHT_FRAGMENT))
					.commit();
		}
		updateButtonState();
	}

	@Override
	public void onEvent(Event event)
	{
		if (event instanceof ChangeColorEvent)
		{
			container.setBackgroundColor(ColorGenUtils.generateColor());
		}else if (event instanceof FinishEvent){
			isStarted=false;
			updateButtonState();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_settings:
				Intent intent = new Intent(this, PopupActivity_.class);
				this.startActivity(intent);
				return true;
			case R.id.action_finish:
				JEMS.dispatcher().sendEvent(new FinishEvent());
				return true;
			case R.id.action_default:
				JEMS.dispatcher().sendEvent(new DefaultEvent());
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Click(R.id.start)
	protected void  startClick(){
		new Thread(new ChangeColorRunnable()).start();
		isStarted = true;
		updateButtonState();
	}

	private void updateButtonState()
	{
		button.setEnabled(!isStarted);
	}


	@Override
	public void onBackPressed()
	{
		JEMS.dispatcher().sendEvent(new FinishEvent());
		super.onBackPressed();
	}
}