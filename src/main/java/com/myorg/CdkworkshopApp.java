package com.myorg;

import software.amazon.awscdk.core.App;

public final class CdkworkshopApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkworkshopStack(app, "CdkworkshopStack");

        app.synth();
    }
}
