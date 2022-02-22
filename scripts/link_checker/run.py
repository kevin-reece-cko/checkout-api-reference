from link_checker import check_broken_links

print("Checking for ABC broken links", sep="\n")
print("-----------------", sep="\n")
for i in check_broken_links("abc"):
    print(i)
print("-----------------", sep="\n")

print("Checking for NAS broken links", sep="\n")
print("-----------------", sep="\n")
for i in check_broken_links("nas"):
    print(i)
print("-----------------", sep="\n")
