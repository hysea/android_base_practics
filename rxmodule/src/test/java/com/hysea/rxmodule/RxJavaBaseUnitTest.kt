package com.hysea.rxmodule

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RxJavaBaseUnitTest {
    @Test
    fun testFilter() {
        Observable.just("Hello RxJava")
                .subscribe({
                    println(it)
                }, {
                    println(it)
                }, {
                    println("complete")
                })
    }

    @Test
    fun testCombineLatest() {
        val observable1 = Observable.just(1, 3, 5)
        val observable2 = Observable.just(2, 4, 6)

        Observable.combineLatest(observable1, observable2, BiFunction<Int, Int, Int> { t1, t2 ->
            return@BiFunction t1 + t2
        }).subscribe {
            println(it) // 7  9  11
        }
    }

    @Test
    fun testZip() {
        val observable1 = Observable.just(1, 3, 5)
        val observable2 = Observable.just(2, 4, 6)

        Observable.zip(observable1, observable2, BiFunction<Int, Int, Int> { t1, t2 ->
            return@BiFunction t1 + t2
        }).subscribe {
            println(it)  // 3  7  11
        }
    }
}