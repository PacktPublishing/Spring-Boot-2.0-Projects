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

class StringFormatter {

    // Immutable
    val separatorChar: String = ",";

    // Mutable
    var noOfTimesMethodsInvoked = 0;

    fun trimAndPrint(csv: String) {
        println("${csv.trim()}")
    }

    fun csvToList(csv: String): List<String>? {
        if (csv == null) {
            println("Value for Argument can not be null");
            return null;
        }
        noOfTimesMethodsInvoked++;
        return csv.split(separatorChar);
    }

    fun printCsvItems(csv: String) {
        noOfTimesMethodsInvoked++;
        val csvItems = csvToList(csv);
        if (csvItems != null && csvItems.size > 0) {
            for (i in csvItems) {
                println(i);
            }
        }
    }

    fun getStringLength(csv: Any): Int? {
        noOfTimesMethodsInvoked++;
        if (csv is String) {
            return csv.length;
        }

        return null;
    }

}
