package com.hysea.rxmodule

import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun testFilter() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        Observable.fromIterable(list)
                .filter {
                    it % 2 == 0
                }
                .subscribe {
                    println(it)
                }
    }
}