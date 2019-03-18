package com.example.androidjavaandjs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void javaCallJS1(View view){
		Intent intent = new Intent(this,JavaCallJSActivity1.class);
		startActivity(intent);
	}
	
	public void javaCallJS2(View view){
		Intent intent = new Intent(this,JavaCallJSActivity2.class);
		startActivity(intent);
	}
	
	
	public void JSCalljava(View view){
		Intent intent = new Intent(this,JSCallJavaActivity.class);
		startActivity(intent);
	}
	
	
	public void netJsCalljava(View view){
		Intent intent = new Intent(this,NetJSCallJavaActivity.class);
		startActivity(intent);
	}

	public void netJsCalljava2(View view){
		Intent intent = new Intent(this,RealNetJSCallJavaActivity.class);
		startActivity(intent);
	}

	

}
