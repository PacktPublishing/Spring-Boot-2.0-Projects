/*
    Copyright (C) 2018  Shazin Sadakath

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.packtpub.springboot2kotlin.gettingstarted

class Calculator {

    // This is an inline function which adds two Int values
    fun add(n1: Int, n2: Int): Int = n1 + n2;

    /*
    This is a descriptive function which adds two Double values
     */
    fun add(n1: Double, n2: Double): Double {
        return n1 + n2;
    }

    fun substract(n1: Int, n2: Int): Int = n1 - n2;

    fun substract(n1: Double, n2: Double): Double {
        return n1 - n2;
    }

    fun addAndPrint(n1: Int, n2: Int): Unit {
        println("$n1 + $n2 = ${n1 + n2}");
    }

    fun substractAndPrint(n1: Int, n2: Int) {
        println("$n1 - $n2 = ${n1 - n2}");
    }
}
