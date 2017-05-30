package net.ajmichael.churning;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;

final class Aggregator implements Runnable {

  private final UserAgent userAgent;
  private final RedditClient redditClient;

  Aggregator(UserAgent userAgent) {
    this.userAgent = userAgent;
    this.redditClient = new RedditClient(userAgent);
  }

  @Override
  public void run() {
    System.out.println("Hi! I am running!");
    System.out.println(userAgent);
    System.out.println(redditClient);
  }
}
