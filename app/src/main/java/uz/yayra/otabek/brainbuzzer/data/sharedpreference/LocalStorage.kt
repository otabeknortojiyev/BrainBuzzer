package uz.yayra.otabek.brainbuzzer.data.sharedpreference

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreference(context) {
    var name by strings("")
    var money by strings("0")
}