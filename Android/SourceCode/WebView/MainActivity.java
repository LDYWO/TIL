public class MainActivity extends BaseActivity {

  @Override
  protected void InitActivity() {

  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
  setContentView(R.layout.activity_webscan_splash);

  android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
  toolbar.setVisibility(View.INVISIBLE);

  RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.background);

  Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StaticFunctionDialog.OpenUpdateDialog(MainActivity, "Yes", "No", "WebView Example", "", "", Gravity.CENTER, new PopupDialog.Builder.onPopupClickListener() {
                  @Override
                        public void onPopUpClick(View v) {
                            switch (v.getId()) {
                                case R.id.B_Popup_Ok:
                                    StaticFunctionDialog.CloseUpdateDialog();
                                    break;
                                case R.id.B_Popup_Cancel:
                                    StaticFunctionDialog.CloseUpdateDialog();
                                    break;
                            }
                        }
                    });

                });
            }
        });

  setAlwaysDisplayOn();

  }

  @Override
   public void onRestart()
   {
       super.onRestart();

   }

   @Override
   public void onPause() {
       super.onPause();
   }

   @Override
   public void onStop() {
       super.onStop();
   }

   @Override
   public void onResume() {
       super.onResume();
   }


   @Override
   public void onBackPressed()
   {
       Toast.makeText(this, "Bye", Toast.LENGTH_SHORT).show();
   }

   @Override
   public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);

       if(hasFocus) {
           hideSystemBar();
       }

}
