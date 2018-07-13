public void OpenRemoteControl(String packageName) {

       Validation validation = new Validation();
       File file = new File(FilePath);

       if (!validation.statusWifi(this)) {
           Toast.makeText(getApplicationContext(), MultiLan.GetTxt(211), Toast.LENGTH_LONG).show();
       } else {
           Intent startLink = getPackageManager().getLaunchIntentForPackage(packageName);

           if (getPackageList(packageName) == true)
           {
               TeamViewerHandler.sendEmptyMessage(1); // 팀뷰어 설치 되었는지 확인하는 핸들러 루프 종료
               if (file.exists() == true) // ADD_ON 깔린 여부 검사, 패키지명이 존재하지 않음, 파일 존재 유무로 파악
               {
                   startLink.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(startLink);
                   Log.e("3번","실행");
               } else
               {
                   Log.e("2번","실행");
                   AddOnHandler.postDelayed(myTask, 3000); // 3초 후 다운로드 진행
               }
           } else
           {
               UpdateApp updateApp = new UpdateApp(mRoot);
               updateApp.execute("http:/download/teamview_qs.apk");

               TeamViewerHandler.sendEmptyMessage(0); // 팀뷰어 설치 유무 검사 핸들러 실행
               Log.e("1번","실행");
           }
       }
   }

   public boolean getPackageList(String packageName)
   {
       boolean isExist = false;

       PackageManager pkgMgr = getPackageManager();
       List<ResolveInfo> mApps;
       Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
       mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
       mApps = pkgMgr.queryIntentActivities(mainIntent,0);

       try{
           for(int i = 0; i < mApps.size(); i++){
               if(mApps.get(i).activityInfo.packageName.startsWith(packageName)){
                   isExist = true;
                   break;
               }
           }
       }
       catch (Exception e){
           isExist = false;
       }
       return isExist;
   }

   Handler TeamViewerHandler = new Handler()
   {
       @Override
       public void handleMessage (Message msg)
       {
           switch (msg.what)
           {
               case 0:
                   String packageName = "com.teamviewer.quicksupport.market";
                   boolean isExist = getPackageList(packageName);

                   if (isExist == true)
                   {
                       OpenRemoteControl(packageName);
                   }

                   this.sendEmptyMessageDelayed(0, 1000);
                   break;
               case 1:
                   this.removeMessages(0);
                   break;
           }
       }
   };

   Handler AddOnHandler = new Handler();

   private Runnable myTask = new Runnable() {
       @Override
       public void run() {
           switch (Global.MODELNAME)
           {
               case "HSK08":
                   UpdateApp updateApp = new UpdateApp(mRoot);
                   updateApp.execute("http:/download/addon_teclast.apk");
                   break;
               case "Lenovo TB-X304F":
                   UpdateApp updateApp2 = new UpdateApp(mRoot);
                   updateApp2.execute("http:/download/addon_lenovo.apk");
                   break;
               case "Lenovo 7인치":
                   UpdateApp updateApp3 = new UpdateApp(mRoot);
                   updateApp3.execute("http:/download/addon_lenovo.apk");
                   break;
               case "Asus 8인치":
                   UpdateApp updateApp4 = new UpdateApp(mRoot);
                   updateApp4.execute("http:/download/addon_asus.apk");
                   break;
               default:
                   Intent startLink = getPackageManager().getLaunchIntentForPackage("com.teamviewer.quicksupport.market");
                   startLink.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(startLink);
           }
       }
   };
