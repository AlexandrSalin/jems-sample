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

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import by.salin.apps.jems.EventHandlerCallback;
import by.salin.apps.jems.JEMS;
import by.salin.apps.jems.impl.Event;
import by.salin.apps.jems.sample.events.ChangeColorEvent;
import by.salin.apps.jems.sample.events.DefaultEvent;
import by.salin.apps.jems.sample.events.SpeedChangeEvent;
import by.salin.apps.jems.sample.utils.ColorGenUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * @author Alexandr.Salin (salin.alexandr@gmail.com)
 */
@EFragment(R.layout.frg_sample)
public class SampleFragment extends Fragment implements EventHandlerCallback
{
	private  String text;
	@ViewById(R.id.seek_bar)
	SeekBar seekBar;
	@ViewById(R.id.container)
	View container;
	@ViewById(R.id.btn)
	TextView titleView;

	public static SampleFragment newInstance(String title)
	{
		SampleFragment result = new SampleFragment_();
		result.setText(title);
		return result;
	}

	public void setText(String title)
	{
		this.text = title;
	}

	@Override
	public void onStart()
	{
		super.onStart();
		JEMS.dispatcher().addListenerOnEvent(DefaultEvent.class, this);
		JEMS.dispatcher().addListenerOnEvent(SpeedChangeEvent.class, this);
		JEMS.dispatcher().addListenerOnEvent(ChangeColorEvent.class, this);
	}

	@Override
	public void onStop()
	{
		super.onStop();
		JEMS.dispatcher().removeListener(this);
	}

	@AfterViews
	void init()
	{
		if (text != null)
		{
			titleView.setText(text);
		}

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser)
			{
				if (!fromUser)
				{
					return;
				}
				sendEvent(i);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				//STUB
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				//STUB
			}

			private void sendEvent(int speedValume)
			{
				JEMS.dispatcher().sendEvent(new SpeedChangeEvent(speedValume));
			}
		});
	}

	@Override
	public void onEvent(Event event)
	{
		if (event instanceof SpeedChangeEvent)
		{
			seekBar.setProgress(((SpeedChangeEvent) event).getSpeedValume());
		}
		else if (event instanceof ChangeColorEvent)
		{
			container.setBackgroundColor(ColorGenUtils.generateColor());
		}
	}
}
