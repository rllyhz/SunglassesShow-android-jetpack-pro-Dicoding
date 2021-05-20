package id.rllyhz.sunglassesshow.utils

import androidx.paging.PagedList
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt

object PagedListUtils {
    fun <T> mockPagedList(listData: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            listData[index]
        }
        `when`(pagedList.size).thenReturn(listData.size)

        return pagedList
    }
}