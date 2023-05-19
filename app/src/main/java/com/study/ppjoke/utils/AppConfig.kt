package com.study.ppjoke.utils

import android.util.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.study.ppjoke.model.BottomBar
import com.study.ppjoke.model.Destination
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 *
 * @author jingqingqing
 * @version
 */
object AppConfig {
    private var sDestConfig: HashMap<String, Destination>? = null
    private var sBottomBar: BottomBar? = null

    fun getDestConfig(): HashMap<String, Destination>? {
        if (sDestConfig == null) {
            val content: String = parseFile("destination.json")
            Log.e("jingqingqing", content)
            sDestConfig = JSON.parseObject(content, object: TypeReference<HashMap<String, Destination>>() {})
        }
        return sDestConfig
    }

    fun getBottomBarConfig(): BottomBar? {
        if (sBottomBar == null) {
            val content = parseFile("main_tabs_config.json")
            sBottomBar = JSON.parseObject(content, BottomBar::class.java)
        }
        return sBottomBar
    }

    private fun parseFile(fileName: String): String {
        val asserts = AppGlobals.getApplication()?.assets ?: return ""

        var input: InputStream? = null
        var br: BufferedReader? = null
        val builder: StringBuilder = StringBuilder()
        try {
            input = asserts.open(fileName)
            br = BufferedReader(InputStreamReader(input))
            var line: String? = null
            line = br.readLine()
            while (line != null) {
                builder.append(line)
                line = br.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                input?.close()
                br?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return builder.toString()
    }
}