require linux-intel.inc

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME", True) == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-intel-rt to enable it")
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
                   file://objtool-fix-segfault-with-clang.patch \
                   file://x86-entry-Emit-a-symbol-for-register-restoring-thunk.patch \
                   "

KBRANCH = "5.10/preempt-rt"
KMETA_BRANCH = "yocto-5.10"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.10.12"
SRCREV_machine ?= "67938f51cc0319dd29e5be856ace30fd0bfed1e6"
SRCREV_meta ?= "513e9332a0092795b3b29eec0030b462ae4c8b6e"

LINUX_KERNEL_TYPE = "preempt-rt"
