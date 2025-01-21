package abdulrahman.ali19

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

