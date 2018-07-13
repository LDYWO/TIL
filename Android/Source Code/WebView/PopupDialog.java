public class PopupDialog extends Dialog
{
  public static final int POPUP_INFO_TWO_BTN_UPDATE = 0x11; // mType

  public PopupDialog(Context context, int type)
  {
    super(context, type);
    setOwnerActivity((Activity) context);
  }

  @Override
  public boolean isShowing()
  {
    return super.isShowing();
  }

  public void setPosition(int y)
  {
    WindowManager.LayoutParams lp = getWindow().getAttributes();

    lp.width = 800;
    lp.height = 550;

    getWindow().setGravity(Gravity.CENTER);
    getWindow().setAttribute(lp);
  }

  public static class Builder
  {
    private Context mContext;
    private String mMessage;
    private String mTitle;
    private View mContentView;
    private TextView txtMessage;
    private ProgressBar progressUpdate;
    private int mType;
    public onPopupClickListener mOnPopupClickListener;

    private String mBtnText1;
    private String mBtnText2;

    private String strSetMessage1;
    private Stirng strSetMessage2;
    private String strSetText;

    private String mTitle1;
    private String mTitle2;

    public interface onPopupClickListener
    {
      public void onPopupClick(View v);
    }

    public Builder (Context context, int type)
    {
      this.mContext = context;
      mType = type;
      mTitle = title;
      mMessage = message;
      mBtnText1 = btnText1;
      mBtnText2 = btnText2;
    }

    public void setOnPopupClickListener(onPopupClickListener iOnPopupClickListener)
    {
      mOnPopupClickListener = iOnPopupClickListener;
    }

    public View getCheckAnimationView() { return mContentView; }

    public void SetMessage (String strMessage) { txtMessage.setText(strMessage); }

    public void SetGravity (int nGravity) { txtMessage.setGravity(nGravity); }

    public int GetMessageType() { return mType; }

    public PopupDialog create()
    {
      LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      DisplayMetrics dm = mContext.getApplicationContext().getResources().getDisplayMetrics();

      int width = dm.widthPixels;
      int height = dm.heightPixels;

      switch (mType)
      {
        case POPUP_INFO_TWO_BTN_UPDATE:
        {
          final PopupDialog dialog = new PopupDialog(mContext, R.style.popUp);
          mContentView = inflater.inflate(R.layout.popup_update_layout, null);

          mContentView.findViewById(R.id.RL_OneBtnPopup_Body).getLayoutParams().width = (int)(width*0.9f);
          mContentView.findViewById(R.id.popup).getLayoutParams().width = (int)(width*0.9f - 100);
          mContentView.findViewById(R.id.popup).getLayoutParams().height = (int)(height*0.9f - 220);

          mContentView.findViewById(R.id.RL_OneBtnPopup_Top).setBackgroundResource(R.drawable.popup_notice_topbg);
          mContentView.findViewById(R.id.RL_OneBtnPopup_Body).setBackgroundResource(R.drawable.popup_notice_centerbg);
          mContentView.findViewById(R.id.RL_OneBtnPopup_Bottom).setBackgroundResource(R.drawable.popup_notice_bottombg);

          dialog.addContentView(mContentView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

          WebView webview = (WebView) mContentView.findViewById(R.id.webview);

          webview.getSettings().setJavaScriptEnabled(true);
          webview.loadUrl("http://google.com");
          webview.setWebViewClient(new WebViewClientClass());

          tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text_title));
          if (mTitle != null) tv.setText(mTitle);
          tv.setGravity(Gravity.CENTER);

          TextView tv = (TextView) mContentView.findViewById(R.id.TV_PopupTitle);
          txtMessage = (TextView) mContentView.findViewById(R.id.TV_PopupMessage);
          txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text_sub_title));
          if (mMessage != null) txtMessage.setText(mMessage);
          txtMessage.setPadding(20, 20, 20, 20);

          dialog.setContentView(mContentView);

          Button b = (Button) mContentView.findViewById(R.id.B_Popup_Ok);
          b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text_content));
          if (mBtnText1 != null) b.setText(mBtnText1);
          b.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v)
              {
                  if (mOnPopupClickListener != null)
                  {
                      mOnPopupClickListener.onPopUpClick(v);
                  }
              }
          });

          b = (Button) mContentView.findViewById(R.id.B_Popup_Cancel);
          b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text_content));

          if (mBtnText2 != null) b.setText(mBtnText2);
          b.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v)
              {
                  if (mOnIKPopupClickListener != null)
                  {
                      mOnIKPopupClickListener.onPopUpClick(v);
                  }
              }

          });

          return dialog;
        }
      }

      return null;
    }

    private class WebViewClientClass extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);

        switch (errorCode) {
            case ERROR_AUTHENTICATION: // 서버에서 사용자 인증 실패
            case ERROR_BAD_URL: // 잘못된 URL
            case ERROR_CONNECT: // 서버로 연결 실패
            case ERROR_FAILED_SSL_HANDSHAKE: // SSL handshake 수행 실패
            case ERROR_FILE: // 일반 파일 오류
            case ERROR_FILE_NOT_FOUND: // 파일을 찾을 수 없습니다
            case ERROR_HOST_LOOKUP: // 서버 또는 프록시 호스트 이름 조회 실패
            case ERROR_IO: // 서버에서 읽거나 서버로 쓰기 실패
            case ERROR_PROXY_AUTHENTICATION: // 프록시에서 사용자 인증 실패
            case ERROR_REDIRECT_LOOP: // 너무 많은 리디렉션
            case ERROR_TIMEOUT: // 연결 시간 초과
            case ERROR_TOO_MANY_REQUESTS: // 페이지 로드중 너무 많은 요청 발생
            case ERROR_UNKNOWN: // 일반 오류
            case ERROR_UNSUPPORTED_AUTH_SCHEME: // 지원되지 않는 인증 체계
            case ERROR_UNSUPPORTED_SCHEME:
                view.loadUrl("about:blank"); // URL이 잘못되었을 때 뜨는 페이지
                break;
            }
        }
    }
  }
}
