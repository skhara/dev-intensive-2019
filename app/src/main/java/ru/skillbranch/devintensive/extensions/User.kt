package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView

fun User.toUserView() : UserView {

    val nickname = ""
    val initials = ""
    val status = if (lastVisit == null) "Ещё ни разу не был"
    else if (isOnline) "online"
    else "Последний раз был ${lastVisit!!.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        initials = initials,
        avatar = avatar,
        status = status
        )
}