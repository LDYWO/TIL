public class StaticFunctionDialog
{
    private static boolean isCreatedUpdateDialog = false; // 다이얼로그가 생성되었는지 확인하기 위한 변수
    private static Dialog dlgUpdate;
    private static PopupDialog.Builder popupUpdate;

    public static void OpenUpdateDialog (Context context, String strTitle, String strMessage, String strFistBtn, String strSecondBtn, int nGravity, onPopupClickListener listenr)
    {
        isCreatedUpdateDialog = false;

        if (strFistBtn.isEmpty()) strFistBtn = "Yes";
        if (strSecondBtn.isEmpty()) strSecondBtn = "No";

        popupUpdate = new PopupDialog.Builder(context,
                PopupDialog.PopupDialog.POPUP_INFO_TWO_BTN_UPDATE,
                strTitle,
                strMessage,
                strFirstBtn, strSecondBtn);

        dlogUpdate = popupUpdate.create();
        popupUpdate.SetGravity(nGravity);
        popupUpdate.setOnPopupClickListener(listener);

        dlgUpdate.setCancelable(false);
        dlgUpdate.show();

        isCreatedUpdateDialog = true;
    }

    public static void CloseUpdateDialog()
    {
      isCreatedUpdateDialog = false;
      dlgUpdate.dismiss();
    }

}
