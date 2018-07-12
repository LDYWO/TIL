
GetXMLTask task = new GetXMLTask(); // XML을 파싱해서 Global.UpdateList에 저장
task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125"); // 예외처리 하기

private class GetXMLTask extends AsyncTask <String, Void, Document>{

    URL url;

    @Override
    protected Document doInBackground(String... urls) {
        try {
            url = new URL(urls[0]);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    @Override
    protected void onPostExecute(Document doc) {

        String s = "";
        // data 태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
        NodeList nodeList = doc.getElementsByTagName("data");
        // data 태그를 가지는 노드를 찾음. 계층적인 노드 구조를 반환

        for (int i = 0; i<nodeList.getLength(); i++){
            // 날씨 데이터를 추출
            s += "" + i + ": 날씨 정보";
            Node node = nodeList.item(i);
            Element fstElmnt =(Element)node;
            NodeList nameList = fstElmnt.getElementsByTagName("temp");
            Element nameElement = (Element) nameList.item(0);
            nameList = nameElement.getChildNodes();
            s += " 온도 =" + ((Node) nameList.item(0)).getNodeValue() + " ,";

            NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
            s += "날씨 =" + websiteList.item(0).getChildNodes().item(0).getNodeValue() +"\n";
        }

        Global.UpdateList = s;// 로컬에 저장 (업데이트 리스트)

        super.onPostExecute(doc);
    }
}
