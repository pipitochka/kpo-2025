package hse.file.interfaces;

import hse.domains.facade.HseFacade;

/**
 * interface of visitor.
 */
public interface Visitor {
    void visit(HseFacade facade, String fileName);
}
