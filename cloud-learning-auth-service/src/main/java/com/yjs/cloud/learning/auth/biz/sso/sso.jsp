<%@ page language="java" import="weaver.hrm.User,java.io.*,java.net.HttpURLConnection"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.net.URL" %>
<%!String login = "";%>
<%!String token = "";%>
<%!String userId = "";%>
<%
    User user = (User) session.getAttribute("weaver_user@bean");
    userId = user.getLoginid();
    //获取token
    String clientId = "de1a76b0e3a8478894ef614eab185f12", clientSecret = "NTdkZGZkYjdiOTNkMTQ1NDE4ZGJiNzYxNWQxMTVhMDM=";
    String url = "http://learncommunity.vaiwan.com/login/sso";
    String params = "{\"clientId\": \"" + clientId + "\", \"clientSecret\": \"" + clientSecret + "\", \"username\": \"" + userId + "\"}";
    token = transport(url, params);
    if (token.length() > 0) {
        String[] split = token.split("\"access_token\":\"");
        token = split[1];
        int end = token.indexOf("\"");
        token = token.substring(0, end);
    } else {
        return;
    }
    //获取登录地址
    login = "http://localhost:5559/sso?access_token=" + token;
%>
<%!
    public String transport(String url, String param) {
        StringBuffer sb = new StringBuffer();
        OutputStream os = null;
        BufferedReader in = null;
        try {
            URL urls = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
            uc.setRequestMethod("POST");
            uc.setRequestProperty("content-type", "application/json");
            uc.setRequestProperty("charset", "UTF-8");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setReadTimeout(10000);
            uc.setConnectTimeout(10000);
            os = uc.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.write(param.getBytes());
            dos.flush();

            in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                sb.append(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
%>

<html>
<head>
</head>
<body>
</body>
</html>
<script>
    window.onload = function () {

        window.location.href = "<%=login%>";
    }

</script>
