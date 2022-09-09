package br.ufrn.imd.repositories;


import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.conventions.DocumentConventions;

public class DocumentStoreHolder {
    private static class DocumentStoreContainer {
        public static final IDocumentStore store;


        static {

            //Create new Document Store with the url of the RavenDB Server
            //and with `Hospital` set as the default database
            store = new DocumentStore(new String[]{ "http://localhost:8080" }, "ImdBank");

            DocumentConventions conventions = store.getConventions();

            store.initialize();
        }
    }

    public static IDocumentStore getStore() {
        return DocumentStoreContainer.store;
    }
}