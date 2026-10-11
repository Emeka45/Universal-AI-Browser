package com.emeka45.universal.aibrowser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;
import org.mozilla.geckoview.WebExtension;

public final class MainActivity extends Activity {
    private static final String TAG = "UniversalAI";
    private static final String IDLEN_LOCATION = "resource://android/assets/idlen/";
    private static final String IDLEN_ID = "extension@idlen.io";
    private static final String CHATGPT_URL = "https://chatgpt.com/";

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

        installIdlenThenOpenChatGPT();
    }

    private void installIdlenThenOpenChatGPT() {
        runtime.getWebExtensionController()
                .ensureBuiltIn(IDLEN_LOCATION, IDLEN_ID)
                .accept(
                        extension -> {
                            Log.i(TAG, "Idlen ready: " + extension);
                            session.loadUri(CHATGPT_URL);
                        },
                        error -> {
                            Log.e(TAG, "Idlen installation failed; opening ChatGPT without Idlen", error);
                            session.loadUri(CHATGPT_URL);
                        });
    }

    @Override protected void onDestroy() {
        if (session != null) session.close();
        super.onDestroy();
    }
}
