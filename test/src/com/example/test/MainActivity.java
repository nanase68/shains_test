package com.example.test;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String EXTRA_MESSAGE = "com.example.test.MESSAGE";
	public static final String OPT_INPUT = "input";
	public static final String OPT_INPUT_STRING = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Preference領域に保存したテキストデータを読み込む
		String inputText = loadFromPreference();

//		final TextView display_text = (TextView) this
//				.findViewById(R.id.display_text);
		final TextView display_text = (TextView) this.findViewById(R.id.editText1);
		display_text.setText(inputText);

		Button go_button = (Button) this.findViewById(R.id.go_button);
		go_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", "Go Button");
				// display_text.setText("Goooooo!");
				gotoTest2();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void gotoTest2() {
		Intent intent = new Intent(this, Test2.class);
		EditText editText = (EditText) findViewById(R.id.editText1);
		String text = editText.getText().toString();
		intent.putExtra(Test2.EXTRA_MESSAGE, text);

		saveToPreference(text);

		startActivity(intent);
	}

	private void saveToPreference(String text) {
		// プリファレンスの準備 //
		// SharedPreferences pref =
		// getSharedPreferences("pref", MODE_PRIVATE );
		SharedPreferences pref = getPreferences(MODE_PRIVATE);

		// プリファレンスに書き込むためのEditorオブジェクト取得 //
		Editor editor = pref.edit();

		editor.putString("inputText", text);

		// 書き込みの確定（実際にファイルに書き込む）
		editor.commit();
	}

	private String loadFromPreference() {
		// SharedPreferences pref =
		// getSharedPreferences("pref", MODE_PRIVATE);
		SharedPreferences pref = getPreferences(MODE_PRIVATE);
		String str = pref.getString("inputText", "");
		return str;
	}
}
