package net.ajmichael.churning;

import com.google.auto.value.AutoValue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import net.dean.jraw.http.UserAgent;

public final class App {

  @AutoValue
  abstract static class Params {

    static Params load() throws IOException {
      Properties properties = new Properties();
      InputStream input = new FileInputStream("gradle.properties");
      properties.load(input);
      return new AutoValue_App_Params(
          properties.getProperty("platform"),
          properties.getProperty("appId"),
          properties.getProperty("version"),
          properties.getProperty("username"),
          Integer.parseInt(properties.getProperty("threadPoolSize")));
    }

    UserAgent userAgent() {
      return UserAgent.of(platform(), appId(), version(), username());
    }

    abstract String platform();

    abstract String appId();

    abstract String version();

    abstract String username();

    abstract int threadPoolSize();
  }

  public static void main(String[] args) throws IOException {
    Params params = Params.load();
    Aggregator aggregator = new Aggregator(params.userAgent());
    Executors.newFixedThreadPool(params.threadPoolSize()).submit(aggregator);
  }
}
