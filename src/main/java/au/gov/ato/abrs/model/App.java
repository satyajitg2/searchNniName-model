package au.gov.ato.abrs.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // writeToStandardOutputWithModuleJsonSchema("uri.v3_external_search_nni_name_asic_gov.SearchNniNameReplyType",
        // "target/SearchNniNameReplyType.json.schema");
        // writeToStandardOutputWithModuleJsonSchema("au.gov.ato.abrs.model.nninamesearch.NniNameSearchResult",
        // "target/NniNameSearchResult.json.schema");
        createJsonSchemaForClass(au.gov.ato.abrs.model.nninamesearch.NniNameSearchResult.class, new File("target/NniNameSearchResult.json.schema"));
        out.println("Complete");
    }

    private static void createJsonSchemaForClass(Class c, File dst) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // configure mapper, if necessary, then create schema generator
            JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
            com.fasterxml.jackson.module.jsonSchema.JsonSchema schema = schemaGen.generateSchema(c);
            Files.write(dst.toPath(), mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(schema));
            out.println("Wrote JSON Schema for class " + c.getName() + " to file " + dst.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeToStandardOutputWithModuleJsonSchema(final String fullyQualifiedClassName,
            String filename) {
        final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        visitor.setVisitorContext(new VisitorContext() {
            @Override
            public String addSeenSchemaUri(JavaType aSeenSchema) {
                return javaTypeToUrn(aSeenSchema);
            }
        });

        final ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.acceptJsonFormatVisitor(mapper.constructType(Class.forName(fullyQualifiedClassName)), visitor);
            final com.fasterxml.jackson.module.jsonSchema.JsonSchema jsonSchema = visitor.finalSchema();
            FileWriter fw = new FileWriter(filename);
            fw.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
            out.println("Done");
        } catch (ClassNotFoundException cnfEx) {
            err.println("Unable to find class " + fullyQualifiedClassName);
        } catch (JsonMappingException jsonEx) {
            err.println("Unable to map JSON: " + jsonEx);
        } catch (JsonProcessingException jsonEx) {
            err.println("Unable to process JSON: " + jsonEx);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
