// Copyright 2014 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.analysis;

import com.google.devtools.build.lib.concurrent.ThreadSafety.Immutable;
import com.google.devtools.build.lib.packages.BuiltinProvider;
import net.starlark.java.eval.StarlarkValue;

/** A {@link ConfiguredTarget} that has licensed targets in its transitive closure. */
@Immutable
public final class LicensesProviderImpl implements LicensesProvider, StarlarkValue {
  public static final LicensesProvider EMPTY = new LicensesProviderImpl();

  public LicensesProviderImpl() {}

  @Override
  public BuiltinProvider<LicensesProvider> getProvider() {
    return LicensesProvider.PROVIDER;
  }

  /** Create the appropriate {@link LicensesProvider} for a rule based on its {@code RuleContext} */
  public static LicensesProvider of(RuleContext ruleContext) {
    return EMPTY;
  }
}
