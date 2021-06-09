// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.remote.http;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.testing.UnitTests;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.http.HttpMethod.GET;
import static org.openqa.selenium.remote.http.UrlPath.ROUTE_PREFIX_KEY;

@Category(UnitTests.class)
public class UrlPathTest {

  @Test
  public void shouldAssumeARegularHttpRequestHasNoPrefix() {
    HttpRequest req = new HttpRequest(GET, "/cheese");

    String absolute = UrlPath.relativeToServer(req, "/cake");
    assertThat(absolute).isEqualTo("/cake");

    String relative = UrlPath.relativeToContext(req, "/cake");
    assertThat(relative).isEqualTo("/cake");
  }

  @Test
  public void shouldRedirectARequestWithAPrefixAttribute() {
    HttpRequest req = new HttpRequest(GET, "/cake");
    req.setAttribute(ROUTE_PREFIX_KEY, singletonList("/cheese"));

    String absolute = UrlPath.relativeToServer(req, "/cake");
    assertThat(absolute).isEqualTo("/cake");

    String relative = UrlPath.relativeToContext(req, "/cake");
    assertThat(relative).isEqualTo("/cheese/cake");
  }

}
