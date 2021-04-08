import sys

base_template = """
# JDA v4 tutorial series


### Episode list

"""


print(base_template)


count = 28

if len(sys.argv) > 1:
    count = int(sys.argv[1])


for i in range(1, count + 1):
    num = str(i).zfill(2)
    extra_template = f"[Episode {num}](https://github.com/MenuDocs/JDA4-tutorials/tree/EP{num}) <br />\n"

    base_template += extra_template


print(base_template)

with open('README.md', 'w') as f:
    f.write(base_template)
