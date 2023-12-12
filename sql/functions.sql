CREATE OR REPLACE FUNCTION log_action()
    RETURNS TRIGGER AS
$body$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO logging (action, action_time, new_data)
        VALUES ('INSERT', NOW(), row_to_json(NEW));
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO logging (action, action_time, old_data, new_data)
        VALUES ('UPDATE', NOW(), row_to_json(OLD), row_to_json(NEW));
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO logging (action, action_time, old_data)
        VALUES ('DELETE', NOW(), row_to_json(OLD));
    END IF;
    RETURN NEW;
END;
$body$
    LANGUAGE plpgsql;

CREATE TRIGGER log_teachers
    AFTER INSERT OR UPDATE OR DELETE
    ON teacher
    FOR EACH ROW
EXECUTE FUNCTION log_action();

CREATE TRIGGER log_class
    AFTER INSERT OR UPDATE OR DELETE
    ON class
    FOR EACH ROW
EXECUTE FUNCTION log_action();

CREATE TRIGGER log_subject
    AFTER INSERT OR UPDATE OR DELETE
    ON subject
    FOR EACH ROW
EXECUTE FUNCTION log_action();

CREATE OR REPLACE FUNCTION set_class_end_time()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$body$
BEGIN
    IF NEW.end_time IS NULL AND NEW.start_time IS NOT NULL THEN
        NEW.end_time := NEW.start_time + interval '1 hour 20 minutes';
    END IF;
    RETURN NEW;
END;
$body$;

CREATE TRIGGER check_class_end_time
    BEFORE INSERT
    ON class
    FOR EACH ROW
EXECUTE FUNCTION set_class_end_time();
