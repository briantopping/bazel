// Copyright 2017 The Bazel Authors. All rights reserved.
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

package com.google.devtools.build.buildjar.javac;

import com.google.common.collect.ImmutableList;
import com.google.devtools.build.buildjar.javac.statistics.BlazeJavacStatistics;

/** The result of a single compilation performed by {@link BlazeJavacMain}. */
public class BlazeJavacResult {
  /** The compilation result. */
  public enum Status {
    OK,
    ERROR,
    CRASH,
    REQUIRES_FALLBACK,
    CANCELLED,
  }

  private final Status status;
  private final ImmutableList<FormattedDiagnostic> diagnostics;
  private final String output;
  private final BlazeJavacStatistics statistics;

  public static BlazeJavacResult ok() {
    return createFullResult(Status.OK, ImmutableList.of(), "", BlazeJavacStatistics.empty());
  }

  public static BlazeJavacResult error(String message) {
    return createFullResult(
        Status.ERROR, ImmutableList.of(), message, BlazeJavacStatistics.empty());
  }

  public static BlazeJavacResult cancelled(String message) {
    return createFullResult(
        Status.CANCELLED, ImmutableList.of(), message, BlazeJavacStatistics.empty());
  }

  public static BlazeJavacResult fallback() {
    return createFullResult(
        Status.REQUIRES_FALLBACK, ImmutableList.of(), "", BlazeJavacStatistics.empty());
  }

  public BlazeJavacResult withStatistics(BlazeJavacStatistics statistics) {
    return new BlazeJavacResult(status, diagnostics, output, statistics);
  }

  private BlazeJavacResult(
      Status status,
      ImmutableList<FormattedDiagnostic> diagnostics,
      String output,
      BlazeJavacStatistics statistics) {
    this.status = status;
    this.diagnostics = diagnostics;
    this.output = output;
    this.statistics = statistics;
  }

  public static BlazeJavacResult createFullResult(
      Status status,
      ImmutableList<FormattedDiagnostic> diagnostics,
      String output,
      BlazeJavacStatistics statistics) {
    return new BlazeJavacResult(status, diagnostics, output, statistics);
  }

  public boolean isOk() {
    return status == Status.OK;
  }

  public Status status() {
    return status;
  }

  public ImmutableList<FormattedDiagnostic> diagnostics() {
    return diagnostics;
  }

  public String output() {
    return output;
  }

  public BlazeJavacStatistics statistics() {
    return statistics;
  }
}
