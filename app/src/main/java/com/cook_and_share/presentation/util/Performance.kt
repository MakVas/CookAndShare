package com.cook_and_share.presentation.util

import com.google.firebase.perf.metrics.Trace
import com.google.firebase.perf.trace

inline fun <T> trace(name: String, block: Trace.() -> T): T = Trace.create(name).trace(block)