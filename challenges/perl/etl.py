import csv

TABLE_NAME = "nasdaq_prices"
DATABASE_ENGINE = "InnoDB"
DEFAULT_CHARSET = "latin1"

filename = "prices.csv"

# Open output files
with open("mysqlCreateSchema.sql", "w") as table_file, open("mysqlInsertValues.sql", "w") as values_file:
    count = 0
    columns_values = ""

    # Read the CSV file
    with open(filename, "r") as file:
        reader = csv.reader(file)
        columns = next(reader)

        # Process column names
        columns = [col.replace("'", "\\'").replace('"', '').replace(' ', '_') for col in columns]
        field_names = columns
        field_names_count = len(field_names)

        # Generate column values string
        if count == 0:
            columns_values = ", ".join(field_names)

        # Process rows
        for row in reader:
            field_values = [value.replace("'", "\\'") for value in row]
            field_values = [value if value else "0" for value in field_values]

            # Determine data types and lengths
            types = [""] * field_names_count
            lengths = [0] * field_names_count
            decimal_length1 = [0] * field_names_count
            decimal_length2 = [0] * field_names_count

            for i, value in enumerate(field_values):
                if any(c.isalpha() for c in value):
                    types[i] = "varchar"
                    lengths[i] = max(lengths[i], len(value))
                elif value.replace('.', '', 1).isdigit():
                    if '.' in value:
                        types[i] = "decimal"
                        int_part, dec_part = value.split('.')
                        decimal_length1[i] = max(decimal_length1[i], len(int_part))
                        decimal_length2[i] = max(decimal_length2[i], len(dec_part))
                    else:
                        types[i] = "int"
                        lengths[i] = max(lengths[i], len(value))
                else:
                    types[i] = "varchar"
                    lengths[i] = max(lengths[i], len(value))

            # Write insert statements
            values_file.write(f"INSERT INTO {TABLE_NAME} ({columns_values})\nVALUES (")
            values_file.write(", ".join(f"'{value}'" for value in field_values))
            values_file.write(");\n")

            count += 1

        # Write CREATE TABLE statement
        table_file.write(f"\n\nCREATE TABLE `{TABLE_NAME}` (\n")
        for i, field_name in enumerate(field_names):
            if types[i] == "decimal":
                decimal_field_length = decimal_length1[i] + decimal_length2[i]
                table_file.write(f" `{field_name}` {types[i]}({decimal_field_length},{decimal_length2[i]})")
            else:
                table_file.write(f" `{field_name}` {types[i]}({lengths[i]})")

            if i < field_names_count - 1:
                table_file.write(",\n")
            else:
                table_file.write("\n")

        table_file.write(f") ENGINE={DATABASE_ENGINE} DEFAULT CHARSET={DEFAULT_CHARSET};\n")
print("hi")
print(f"Processed {len(field_names)} columns and {count} lines.")
print("Process completed.")