from link_checker import check_broken_links
import sys

BROKEN_LINKS_EXIST = False

print("ABC broken links", sep="\n")
print("-----------------", sep="\n")
for i in check_broken_links("abc"):
    print(i)
    if i:
        BROKEN_LINKS_EXIST = True
print("-----------------", sep="\n")

print("NAS broken links", sep="\n")
print("-----------------", sep="\n")
for i in check_broken_links("nas"):
    print(i)
    if i:
        BROKEN_LINKS_EXIST = True
print("-----------------", sep="\n")

if BROKEN_LINKS_EXIST:
    sys.exit(1)
