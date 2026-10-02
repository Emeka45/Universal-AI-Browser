package com.emeka45.universal.aibrowser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;
import org.mozilla.geckoview.WebExtension;

public final class MainActivity extends Activity {
    private static GeckoRuntime runtime;
    private GeckoSession session;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);

        GeckoView view = findViewById(R.id.geckoview);
        if (runtime == null) runtime = GeckoRuntime.create(this);
        session = new GeckoSession();
        session.setContentDelegate(new GeckoSession.ContentDelegate() {});
        session.open(runtime);
        view.setSession(session);

        installIdlen();
        session.loadUri("https://chatgpt.com/");
    }

    private void installIdlen() {
        runtime.getWebExtensionController()
                .ensureBuiltIn("resource://android/assets/idlen/", "idlen@example.com")
                .accept(ext -> Log.i("UniversalAI", "Idlen installed: " + ext),
                        err -> Log.e("UniversalAI", "Idlen install failed", err));
    }

    @Override protected void onDestroy() {
        if (session != null) session.close();
        super.onDestroy();
    }
}
