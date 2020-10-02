package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;

import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;

public class CdkworkshopStack extends Stack {

  public CdkworkshopStack(final Construct parent, final String id) {
    this(parent, id, null);
  }

  public CdkworkshopStack(final Construct parent, final String id, final StackProps props) {
    super(parent, id, props);

    final Function hello = Function.Builder.create(this, "HelloHandler")
        .runtime(Runtime.NODEJS_10_X) // execution
        // environment
        .code(Code.fromAsset("lambda")) // code loaded from the "lambda" directory
        .handler("hello.handler") // file is "hello", function is "handler"
        .build();

    // Defines our hitcounter resource
    final HitCounter helloWithCounter = new HitCounter(this, "HelloHitCounter",
        HitCounterProps.builder()
            .downstream(hello)
            .build());

    // Defines an API Gateway REST API resource backed by our "hello" function
    LambdaRestApi.Builder.create(this, "Endpoint").handler(helloWithCounter.getHandler()).build();
  }
}
