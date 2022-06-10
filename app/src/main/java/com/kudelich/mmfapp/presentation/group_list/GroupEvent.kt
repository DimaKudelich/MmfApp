package com.kudelich.mmfapp.presentation.group_list

sealed class GroupEvent {
    object Refresh : GroupEvent()
    object LoadForSettings : GroupEvent()
}
