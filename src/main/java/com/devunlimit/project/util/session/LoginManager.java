package com.devunlimit.project.util.session;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginManager implements HttpSessionBindingListener {

  private static LoginManager loginManager = null;

  private static Hashtable loginUsers = new Hashtable();

  public static synchronized LoginManager getInstance() {
    if (loginManager == null) {
      loginManager = new LoginManager();
    }
    return loginManager;
  }

  public void valueBound(HttpSessionBindingEvent event) {
    loginUsers.put(event.getSession(), event.getName());
    System.out.println(event.getName() + "님이 로그인 하셨습니다.");
    System.out.println("현재 접속자 수 : " + getUserCount());
  }

  public void valueUnbound(HttpSessionBindingEvent event) {
    loginUsers.remove(event.getSession());
    System.out.println("  " + event.getName() + "님이 로그아웃 하셨습니다.");
    System.out.println("현재 접속자 수 : " + getUserCount());
  }

  public void removeSession(String userId) {
    Enumeration e = loginUsers.keys();
    HttpSession session = null;
    while (e.hasMoreElements()) {
      session = (HttpSession) e.nextElement();
      if (loginUsers.get(session).equals(userId)) {
        session.invalidate();
      }
    }
  }

  public boolean isUsing(String userID) {
    return loginUsers.containsValue(userID);
  }

  public void setSession(HttpSession session, String userId) {
    //이순간에 Session Binding이벤트가 일어나는 시점
    //name값으로 userId, value값으로 자기자신(HttpSessionBindingListener를 구현하는 Object)
    session.setAttribute(userId, this);//login에 자기자신을 집어넣는다.
  }

  public String getUserID(HttpSession session) {
    return (String) loginUsers.get(session);
  }

  public int getUserCount() {
    return loginUsers.size();
  }

  public void printloginUsers() {
    Enumeration e = loginUsers.keys();
    HttpSession session = null;
    System.out.println("===========================================");
    int i = 0;
    while (e.hasMoreElements()) {
      session = (HttpSession) e.nextElement();
      System.out.println((++i) + ". 접속자 : " + loginUsers.get(session));
    }
    System.out.println("===========================================");
  }

  public Collection getUsers() {
    Collection collection = loginUsers.values();
    return collection;
  }
}

