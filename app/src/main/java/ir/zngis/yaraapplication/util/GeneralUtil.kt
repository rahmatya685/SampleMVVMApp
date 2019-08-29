package ir.zngis.yaraapplication.util

import android.content.Context
import android.os.Environment
import java.io.File

object GeneralUtil {
    fun getPicsDirectory(activity: Context): File? {
        val path = File(getAppsDirectory() + File.separator + "Pics")
        if (!path.exists()) path.mkdir()
        return path.absoluteFile
    }
    fun getAppsDirectory():String{
        val path = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "Yara")
        if (!path.exists()) path.mkdir()
        return  path.absolutePath
    }
}
