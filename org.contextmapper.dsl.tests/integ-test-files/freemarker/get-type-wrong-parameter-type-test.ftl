<#list boundedContexts as bc><#list bc.aggregates as agg><#list agg.domainObjects as do><#list do.operations as op>${getType(op)}</#list></#list></#list></#list>