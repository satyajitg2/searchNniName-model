package au.gov.ato.abrs.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        writeToStandardOutputWithModuleJsonSchema(
                "uri.v3_external_search_nni_name_asic_gov.SearchNniNameRequestType");
        out.println( "Complete" );
    }

    private static void writeToStandardOutputWithModuleJsonSchema(
            final String fullyQualifiedClassName)
    {
         final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        visitor.setVisitorContext(new VisitorContext(){
            @Override
            public String addSeenSchemaUri(JavaType aSeenSchema) {
                return javaTypeToUrn(aSeenSchema);
            }
        });

        final ObjectMapper mapper = new ObjectMapper();

        try
        {
            mapper.acceptJsonFormatVisitor(mapper.constructType(Class.forName(fullyQualifiedClassName)), visitor);
            final com.fasterxml.jackson.module.jsonSchema.JsonSchema jsonSchema = visitor.finalSchema();
            out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
        }
        catch (ClassNotFoundException cnfEx)
        {
            err.println("Unable to find class " + fullyQualifiedClassName);
        }
        catch (JsonMappingException jsonEx)
        {
            err.println("Unable to map JSON: " + jsonEx);
        }
        catch (JsonProcessingException jsonEx)
        {
            err.println("Unable to process JSON: " + jsonEx);
        }
    }
}
