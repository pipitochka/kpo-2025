package hse.file.interfaces;

import hse.domains.facade.HseFacade;

public interface Visitor {
    void visit(HseFacade facade, String fileName);
}
